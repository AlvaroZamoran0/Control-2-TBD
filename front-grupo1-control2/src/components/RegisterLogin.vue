<template>
  <div class="register-login-container">
    <div class="form-container">
      <h2>{{ isLogin ? "Iniciar Sesión" : "Registro" }}</h2>

      <form @submit.prevent="isLogin ? handleLogin() : handleRegister()">

        <div class="form-group">
          <label>Nombre de Usuario:</label>
          <input type="text" v-model="username" required />
        </div>

        <!-- Campo para el correo electrónico solo en el registro -->
        <div v-if="!isLogin" class="form-group">
          <label>Correo Electrónico:</label>
          <input type="email" v-model="email" required />
        </div>

        <div class="form-group">
          <label>Contraseña:</label>
          <input type="password" v-model="password" required />
        </div>

        <!-- Confirmación de contraseña solo presente en el registro -->
        <div v-if="!isLogin" class="form-group">
          <label>Confirmar Contraseña:</label>
          <input type="password" v-model="confirmPassword" required />
        </div>

        <button type="submit" class="submit-btn">{{ isLogin ? "Ingresar" : "Registrar" }}</button>
      </form>

      <p class="switch-mode-text">
        {{ isLogin ? "¿No tienes una cuenta?" : "¿Ya tienes una cuenta?" }}
        <button @click="toggleMode" class="toggle-btn">
          {{ isLogin ? "Regístrate aquí" : "Inicia sesión aquí" }}
        </button>
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import usuarioService from "@/services/usuarioService";

export default {
  name: "registerLogin",
  data() {
    return {
      isLogin: true, 
      username: "",
      email: "",     
      password: "",
      confirmPassword: "", 
    };
  },
  methods: {
    // Boton asociado al cambio del formulario de login a registro y viceversa
    toggleMode() {
      this.isLogin = !this.isLogin;
      this.clearFields();
    },

    // Funcion que limpia los campos de username, email, password y confirmPassword, cada vez que se cambia de modo
    clearFields() {
      this.username = "";
      this.email = "";   
      this.password = "";
      this.confirmPassword = "";
    },

    // Funcion que maneja el inicio de sesion del usuario login
    async handleLogin() {
      try {
        const response = await loginService.login(this.username, this.password);

        // Verificar que el backend regrese los datos necesarios
        if (response.data.userId) {
          localStorage.setItem("userId", response.data.userId);
          localStorage.setItem("nombreUsuario", response.data.nombre);
          this.$router.push("/home");
        } else {
          alert("No se encontraron datos del usuario. Verifica las credenciales.");
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          alert("Credenciales incorrectas. Intenta nuevamente.");
        } else {
          alert("Error al iniciar sesión. Intenta más tarde.");
        }
        console.error("Error en el inicio de sesión:", error);
      }
    },

    // Funcion que maneja el registro de un nuevo usuario
    async handleRegister() {
      if (this.password !== this.confirmPassword) {
        alert("Las contraseñas no coinciden.");
        return;
      }
      try {
        await usuarioService.register({
          username: this.username,
          email: this.email,
          password: this.password,
        });
        alert("Registro exitoso. Ahora puedes iniciar sesión.");
        this.$router.push("/");
      } catch (error) {
        alert("Error al registrarse. Intenta nuevamente.");
      }
    },
  },
};
</script>

<style scoped>
.register-login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f4f8;
}

.form-container {
  width: 100%;
  max-width: 420px;
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  text-align: center;
}

h2 {
  font-family: 'Arial', sans-serif;
  color: #2c3e50;
  margin-bottom: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
  margin-bottom: 1.2rem;
}

label {
  font-size: 1rem;
  color: #7f8c8d;
}

input {
  padding: 0.8rem;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border 0.3s ease-in-out;
}

input:focus {
  outline: none;
  border-color: #3498db;
}

.submit-btn {
  padding: 0.8rem;
  font-size: 1rem;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
}

.submit-btn:hover {
  background-color: #2980b9;
}

.switch-mode-text {
  margin-top: 1rem;
  font-size: 0.9rem;
  color: #7f8c8d;
}

.toggle-btn {
  background: none;
  border: none;
  color: #3498db;
  font-weight: bold;
  cursor: pointer;
  transition: color 0.3s ease;
}

.toggle-btn:hover {
  color: #2980b9;
}
</style>

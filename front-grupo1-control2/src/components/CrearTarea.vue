<template>
  <div class="crear-tarea">
    <h2>Crear Nueva Tarea</h2>
    <form @submit.prevent="handleCreateTask">
      <!-- Nombre de la tarea -->
      <div class="form-group">
        <label for="nombre">Nombre de la tarea:</label>
        <input type="text" v-model="nombre" required placeholder="Introduce el nombre de la tarea" />
      </div>

      <!-- Descripción de la tarea -->
      <div class="form-group">
        <label for="descripcion">Descripción de la tarea:</label>
        <textarea v-model="descripcion" required placeholder="Escribe una breve descripción de la tarea"></textarea>
      </div>

      <!-- Fecha de término -->
      <div class="form-group">
        <label for="fechaTermino">Fecha de término:</label>
        <input type="date" v-model="fechaTermino" required />
      </div>

      <button type="submit">Crear Tarea</button>
    </form>
  </div>
</template>

<script>
import tareaService from "@/services/tareaService";

export default {
  name: "CrearTarea",
  data() {
    return {
      nombre: "", 
      descripcion: "",
      fechaTermino: "",
      idUser: localStorage.getItem("id") || null,
      status: false, // false = pendiente, true = completada
    };
  },

  methods: {
    async handleCreateTask() {
      if (!this.idUser) {
        alert("No se encontró el usuario autenticado.");
        return;
      }

      const newTask = {
        nombre: this.nombre,
        descripcion: this.descripcion,
        fechaTermino: this.fechaTermino,
        idUser: this.idUser,
        status: this.status,
      };

      try {
        const response = await tareaService.crearTarea(newTask);
        console.log("Tarea creada:", response.data);
        alert("Tarea creada exitosamente!");
        this.nombre = "";
        this.descripcion = "";
        this.fechaTermino = "";
        this.$router.push("/home").then(() => {
          window.location.reload();
        });
        
      } catch (error) {
        console.error("Error al crear la tarea:", error);
        alert("Hubo un error al crear la tarea. Intenta nuevamente.");
      }
    },
  },
};
</script>

<style scoped>
.crear-tarea {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  color: #2c3e50;
  font-size: 24px;
  margin-bottom: 20px;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-size: 1rem;
  color: #7f8c8d;
  margin-bottom: 5px;
  display: block;
}

input, textarea {
  width: 100%;
  padding: 10px;
  font-size: 1rem;
  border: 1px solid #ccc;
  border-radius: 4px;
  transition: border 0.3s ease;
}

input:focus, textarea:focus {
  outline: none;
  border-color: #3498db;
}

textarea {
  resize: vertical;
  min-height: 100px;
}

button {
  background-color: #3498db;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
  width: 100%;
}

button:hover {
  background-color: #2980b9;
}

button:active {
  background-color: #1c6691;
}
</style>

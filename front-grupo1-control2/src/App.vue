<template>
  <div id="app">
    <!-- Barra de navegación fija en la parte superior -->
    <nav class="navbar">
      <ul>
        <li><router-link to="/">Login</router-link></li>
        <li><router-link to="/home">Home</router-link></li>
        <li><router-link to="/crearTarea">Crear Tarea</router-link></li>
        <li><router-link to="/listaTareas">Tareas</router-link></li>
      </ul>
    </nav>
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #2c3e50;
  color: white;
  padding: 10px 0;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.navbar ul {
  list-style: none;
  display: flex;
  justify-content: center;
  margin: 0;
  padding: 0;
}

.navbar ul li {
  margin: 0 20px;
}

.navbar ul li a {
  color: white;
  text-decoration: none;
  font-size: 1.1rem;
}

.navbar ul li a:hover {
  text-decoration: underline;
}


.content {
  margin-top: 60px; 
  padding: 20px;
}
</style>

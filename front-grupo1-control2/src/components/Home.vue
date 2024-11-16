<template>
  <div class="home">
    <h1>Bienvenido, Usuario</h1>
    <p>¡Bienvenido a tu panel de control!</p>
  </div>
</template>

<script>
export default {
  name: "Home",
};
</script>

<style scoped>
.home {
  text-align: center;
  padding-top: 100px; /* Ajusta según sea necesario */
}

h1 {
  font-size: 2rem;
  color: #2c3e50;
}

p {
  font-size: 1.2rem;
  color: #34495e;
}
</style>

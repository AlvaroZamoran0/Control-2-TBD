<template>
  <div class="crear-tarea">
    <h2>Crear Nueva Tarea</h2>
    <form @submit.prevent="handleCreateTask">
      <!-- Nombre de la tarea -->
      <div class="form-group">
        <label for="taskName">Nombre de la tarea:</label>
        <input type="text" v-model="taskName" required placeholder="Introduce el nombre de la tarea" />
      </div>

      <!-- Descripción de la tarea -->
      <div class="form-group">
        <label for="taskDescription">Descripción de la tarea:</label>
        <textarea v-model="taskDescription" required placeholder="Escribe una breve descripción de la tarea"></textarea>
      </div>

      <!-- Fecha de vencimiento -->
      <div class="form-group">
        <label for="taskDueDate">Fecha de vencimiento:</label>
        <input type="date" v-model="taskDueDate" required />
      </div>

      <button type="submit">Crear Tarea</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "CrearTarea",
  data() {
    return {
      taskName: "",
      taskDescription: "",
      taskDueDate: "", // Campo para la fecha de vencimiento
      userId: localStorage.getItem('userId') || null, // Obtener el ID del usuario desde el localStorage
    };
  },
  methods: {
    // Lógica para crear la tarea
    async handleCreateTask() {
      if (!this.userId) {
        alert("No se encontró el usuario autenticado.");
        return;
      }

      // Crear el objeto de la tarea
      const newTask = {
        taskName: this.taskName,
        taskDescription: this.taskDescription,
        taskDueDate: this.taskDueDate,
        userId: this.userId, // Asociar el ID del usuario a la tarea
      };

      // Aquí podrías hacer una llamada a la API para guardar la tarea en la base de datos
      console.log("Tarea creada:", newTask);

      // Limpiar los campos después de crear la tarea
      this.taskName = "";
      this.taskDescription = "";
      this.taskDueDate = "";
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

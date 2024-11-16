<template>
    <div class="editar-tarea">
      <h2>Editar Tarea</h2>
      <form @submit.prevent="handleEditTask">
        <!-- Nombre de la tarea -->
        <div class="form-group">
          <label for="nombre">Nombre de la tarea:</label>
          <input
            type="text"
            v-model="nombre"
            required
            placeholder="Introduce el nombre de la tarea"
          />
        </div>
  
        <!-- Descripción de la tarea -->
        <div class="form-group">
          <label for="descripcion">Descripción de la tarea:</label>
          <textarea
            v-model="descripcion"
            required
            placeholder="Escribe una breve descripción de la tarea"
          ></textarea>
        </div>
  
        <!-- Fecha de término -->
        <div class="form-group">
          <label for="fechaTermino">Fecha de término:</label>
          <input type="date" v-model="fechaTermino" required />
        </div>
  
        <!-- Estado -->
        <div class="form-group">
          <label for="status">Estado:</label>
          <select v-model="status" required>
            <option :value="false">Pendiente</option>
            <option :value="true">Completada</option>
          </select>
        </div>
  
        <button type="submit">Guardar Cambios</button>
      </form>
    </div>
  </template>
  
  <script>
  import tareaService from "@/services/tareaService";
  
  export default {
    name: "EditarTarea",
    data() {
      return {
        nombre: "",
        descripcion: "",
        fechaTermino: "",
        status: false,
      };
    },
    async created() {
      try {
        // Obtén el ID desde los parámetros de la ruta
        const taskId = this.$route.params.id;
  
        // Carga los datos de la tarea seleccionada
        const response = await tareaService.getTareasById(taskId);
        const tarea = response.data;
  
        // Asigna los valores al formulario
        this.nombre = tarea.nombre;
        this.descripcion = tarea.descripcion;
        this.fechaTermino = tarea.fechaTermino;
        this.status = tarea.status;
      } catch (error) {
        console.error("Error al cargar la tarea:", error);
        alert("Hubo un problema al cargar los datos de la tarea.");
      }
    },
    methods: {
      async handleEditTask() {
        // Obtén el ID desde los parámetros de la ruta
        const taskId = this.$route.params.id;
  
        const updatedTask = {
          nombre: this.nombre,
          descripcion: this.descripcion,
          fechaTermino: this.fechaTermino,
          status: this.status,
        };
  
        try {
          await tareaService.updateTarea(taskId, updatedTask);
          alert("Tarea actualizada con éxito.");
          this.$router.push("/listaTareas");
        } catch (error) {
          console.error("Error al actualizar la tarea:", error);
          alert("Hubo un error al actualizar la tarea. Intenta nuevamente.");
        }
      },
    },
  };
  </script>
  

  <style scoped>

  .editar-tarea {
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
  
  input,
  textarea,
  select {
    width: 100%;
    padding: 10px;
    font-size: 1rem;
    border: 1px solid #ccc;
    border-radius: 4px;
    transition: border 0.3s ease;
  }
  
  input:focus,
  textarea:focus,
  select:focus {
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
  
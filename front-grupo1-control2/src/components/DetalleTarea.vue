<template>
  <div class="detalle-tarea">
    <h1>Detalle de la Tarea</h1>
    <div v-if="tarea">
      <p class="elem-tabla"><strong>ID:</strong> {{ tarea.id }}</p>
      <p class="elem-tabla"><strong>Nombre:</strong> {{ tarea.nombre }}</p>
      <p class="elem-tabla"><strong>Descripción:</strong> {{ tarea.descripcion }}</p>
      <p class="elem-tabla"><strong>Fecha de Término:</strong> {{ formatearFecha }}</p>
      <p class="elem-tabla"><strong>ID de Usuario:</strong> {{ tarea.idUser }}</p>
      <p
        class="elem-tabla"
        :class="tarea.status ? 'estado-completada' : 'estado-pendiente'"
      >
        <strong>Estado:</strong> {{ tarea.status ? 'Completada' : 'Pendiente' }}
      </p>
      <div class="botones">
        <button 
          class="boton-revisar" 
          @click="marcarCompletado" 
          v-if="!tarea.status"
        >
          Marcar como Completada
      </button>
        <button class="boton-revisar" @click="editarTarea">Editar Tarea</button>
        <button 
          class="boton-eliminar" 
          @click="eliminarTarea"
        >
          Eliminar Tarea
        </button>
      </div>
    </div>
    <div v-else>
      <p>Cargando tarea...</p>
    </div>
  </div>
</template>

<script>
import tareasService from '../services/tareaService';

export default {
  name: 'TareaDetalle',
  data() {
    return {
      tarea: null,
    };
  },
  computed: {
    formatearFecha() {
      if (!this.tarea || !this.tarea.fechaTermino) return 'Sin definir';
      const fecha = new Date(this.tarea.fechaTermino);
      return fecha.toLocaleDateString();
    },
  },
  methods: {
    async traerTarea() {
      try {
        const id = this.$route.params.id;
        const response = await tareasService.getTareasById(id);
        this.tarea = response.data;
      } catch (error) {
        console.error('Error al obtener la tarea:', error);
      }
    },

    async eliminarTarea() {
      if (!this.tarea) {
        return;
      }
      try {
        await tareasService.deleteTarea(this.tarea.id);
        alert('¡Tarea eliminada!');
        this.$router.push("/listaTareas");
      } catch (error) {
        console.error('Error al eliminar la tarea:', error);
      }
    },

    async marcarCompletado() {
      if (!this.tarea) {
        return;
      }
      try {
        const updatedTarea = {
            id: this.tarea.id,
            nombre: this.tarea.nombre,
            descripcion: this.tarea.descripcion,
            fechaTermino: this.tarea.fechaTermino,
            idUser: this.tarea.idUser,
            status: true,
        };
        await tareasService.updateTarea(this.tarea.id, updatedTarea);
        this.tarea.status = true; 
        alert('¡Tarea marcada como completada!');
        this.$router.push("/listaTareas");
        
      } catch (error) {
        console.error('Error al marcar como completada:', error);
      }
    },
    editarTarea() {
      this.$router.push({ name: 'EditarTarea', params: { id: this.tarea.id } });
    },
  },
  mounted() {
    this.traerTarea(); 
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: right;
  margin-top: 20px;
}

.container-tabla {
  width: 80%;
  margin: 0 10px;
}

.detalle-tarea {
  background-color: #f8f9fa;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  width: 60%;
  margin: auto;
}

h1 {
  text-align: center;
  margin: 20px;
  font-size: 25px;
  font-family: Arial, sans-serif;
  color: #2c3e50;
}

.elem-tabla {
  padding: 10px;
  border-bottom: 1px solid #ddd;
  text-align: left;
}

.estado-completada {
  color: #28a745;
  font-weight: bold;
}

.estado-pendiente {
  color: #dc3545;
  font-weight: bold;
}

.botones {
  margin-top: 20px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

.boton-revisar {
  padding: 10px;
  background-color: #386ca0;
  color: white;
  border: 1px solid #386ca0;
  border-radius: 5px;
  cursor: pointer;
}

.boton-revisar:hover {
  background-color: #34495e;
}

.boton-revisar:active {
  background-color: #1c2a39;
  transform: scale(0.98);
  box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
}

.boton-eliminar {
  padding: 10px;
  background-color: #dc3545;
  color: white;
  border: 1px solid #dc3545;
  border-radius: 5px;
  cursor: pointer;
}

.boton-eliminar:hover {
  background-color: #c82333;
}

.boton-eliminar:active {
  background-color: #bd2130;
  transform: scale(0.98);
  box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
}

</style>
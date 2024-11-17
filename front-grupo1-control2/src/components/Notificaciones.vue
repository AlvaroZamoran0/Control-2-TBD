<template>
    <div class="container">
      <div class="container-tabla">
        <table class="tabla-notificaciones">
          <thead>
            <tr>
              <th>ID</th>
              <th>Usuario</th>
              <th>Tarea</th>
              <th>Mensaje</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="notificacion in notificaciones" :key="notificacion.id">
              <td>{{ notificacion.id }}</td>
              <td>{{ notificacion.idUser }}</td>
              <td>{{ notificacion.idTarea }}</td>
              <td>{{ cortarTexto(notificacion.mensaje, 50) }}</td>
              <td>{{ notificacion.leida ? 'Leída' : 'No Leída' }}</td>
              <td>
                <button 
                  class="boton-revisar" 
                  @click="marcarComoLeida(notificacion.id)" 
                  v-if="!notificacion.leida"
                >
                  Marcar como Leída
                </button>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  
  <script>
  import notificacionService from "@/services/notificacionService";
  
  export default {
    name: "ListaNotificaciones",
    data() {
      return {
        notificaciones: [],
        palabraClave: "",
      };
    },
    methods: {
      async obtenerNotificaciones() {
        try {
          const response = await notificacionService.getAllNotificaciones();
          this.notificaciones = response.data;
        } catch (error) {
          console.error("Error al obtener las notificaciones:", error);
        }
      },
      cortarTexto(texto, longitud) {
        return texto.length > longitud ? texto.substring(0, longitud) + "..." : texto;
      },
      async buscarNotificaciones() {
        try {
          const searchKeyword = this.palabraClave.trim() || "empty";
          const response = await notificacionService.searchNotificaciones(null, searchKeyword);
          this.notificaciones = response.data;
        } catch (error) {
          console.error("Error al buscar las notificaciones:", error);
        }
      },
      async marcarComoLeida(id) {
        try {
          await notificacionService.markAsRead(id);
          alert("Notificación marcada como leída");
          this.obtenerNotificaciones();
        } catch (error) {
          console.error("Error al marcar como leída:", error);
        }
      },
    },
    mounted() {
      this.obtenerNotificaciones();
    },
  };
  </script>
  
  <style>
  /* Similar al estilo del componente anterior */
  </style>
  
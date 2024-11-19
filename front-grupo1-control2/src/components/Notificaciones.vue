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
                            <button class="boton-revisar" @click="marcarComoLeida(notificacion.id)" v-if="!notificacion.leida">
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
            idUser: localStorage.getItem("id") || null, // Obtener el ID del usuario desde el localStorage
        };
    },
    methods: {
        async obtenerNotificaciones() {
            try {
                const response = await notificacionService.getNotificacionesByUser(this.idUser);
                this.notificaciones = response.data;
            } catch (error) {
                console.error("Error al obtener las notificaciones:", error);
            }
        },
        async obtenerNotificacionesAND() {
            try {
                const response = await notificacionService.getNotificacionesByUserAND(this.idUser);
                this.notificaciones = response.data;
                
            } catch (error) {
                console.error("Error al obtener las notificaciones:", error);
            }
        },
        cortarTexto(texto, longitud) {
            return texto.length > longitud ? texto.substring(0, longitud) + "..." : texto;
        },
        async tareaPorVencer() {
            try {
                const response = await notificacionService.checkTareasVencidas(this.idUser);
                this.notificaciones = response.data;
            } catch (error) {
                console.error("Error al obtener las tareas por vencer:", error);
            }
        },
        async marcarComoLeida(id) {
            try {
                await notificacionService.checkTarea(id);
                alert("Notificación marcada como leída");
                await this.obtenerNotificacionesAND();
                window.location.reload();
            } catch (error) {
                console.error("Error al marcar como leída:", error);
            }
        },
    },
    async mounted() {
        await this.tareaPorVencer();
        await this.obtenerNotificacionesAND();
    },
};
</script>

<style>
.container {
    display: flex;
    justify-content: center;
    margin-top: 20px;
}

.container-tabla {
    width: 80%;
    margin: 0 10px;
}

table {
    width: 100%;
    border-collapse: collapse;
}

.tabla-notificaciones th {
    background-color: #2c3e50;
    color: white;
    font-weight: bold;
    padding: 10px;
}

th, td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: center;
}

.boton-revisar {
    padding: 10px;
    background-color: #386ca0;
    color: white;
    border: 1px solid #386ca0;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.boton-revisar:hover {
    background-color: #34495e;
}

.boton-revisar:active {
    background-color: #1c2a39;
    transform: scale(0.98);
    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
}
</style>

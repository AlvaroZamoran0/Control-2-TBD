<template>
    <div class="container">
        <div class="container-filtro">
            <h1>Filtros de búsqueda</h1>
            <div class="barra-busqueda">
                <input type="text" placeholder="Busca" class="busqueda-input" v-model="palabraClave">
            </div>
            <div class="filtros">
                <div class="check-tareas">
                    <input type="radio" id="pendientes" name="tareas" value="pendientes" v-model="filtroSeleccionado" @change="actualizarStatus">
                    <label for="pendientes">Tareas pendientes</label>
                </div>
                <div class="check-tareas">
                    <input type="radio" id="completadas" name="tareas" value="completadas" v-model="filtroSeleccionado" @change="actualizarStatus">
                    <label for="completadas">Tareas Completadas</label>
                </div>
                <div class="check-tareas">
                    <input type="radio" id="todo" name="tareas" value="todo" v-model="filtroSeleccionado" @change="actualizarStatus">
                    <label for="todo">Todas las tareas</label>
                </div>
                <div class="boton-buscar-container"><button class="busqueda-boton" @click="filtrarTareas">Buscar</button></div>
            </div>
        </div>
        <div class="container-tabla">
            <table class="tabla-tareas">
                <thead>
                    <tr>
                        <th class="left-box">Nombre</th>
                        <th>Descripción</th>
                        <th>Fecha de Vencimiento</th>
                        <th class="right-box">Estado</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="tarea in tareas" :key="tarea.id">
                        <td class="elem-tabla">{{ tarea.nombre }}</td>
                        <td class="elem-tabla">{{ cortarTexto(tarea.descripcion, 50) }}</td>
                        <td class="elem-tabla">{{ tarea.fechaTermino }}</td>
                        <td class="elem-tabla">{{ tarea.status ? 'Completada' : 'Pendiente'}}</td>
                        <td class="elem-tabla">
                            <button class="boton-revisar" @click="revisarTarea(tarea.id)">Revisar</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import tareaService from '@/services/tareaService';

export default {
    name: 'ListaTareas',

    data() {
        return {
            tareas: [{id: 1, nombre: 'Tarea 1', descripcion: 'Descripción de la tarea 1', fechaTermino: '2021-12-31', status: false}],
            filtroSeleccionado: 'todo',
            userId: 1,
            status: null,
            palabraClave: ""
        };
    },
    methods: {
        async obtenerTareas() {
            try {
                const response = await tareaService.getTareasByUser(this.userId);
                this.tareas = response.data;
            } catch (error) {
                console.error('Error al obtener las tareas:', error);
            }
        },
        cortarTexto(texto, longitud) {
            if (texto.length > longitud) {
                return texto.substring(0, longitud) + '...';
            }
            return texto;
        },
        revisarTarea(tareaId) {
            this.$router.push(`/DetalleTarea/${tareaId}`);
        },
        async filtrarTareas() {
            try {
                const searchKeyword = this.palabraClave.trim() === "" ? "empty" : this.palabraClave.trim();
                let response;
                if (this.status === null) {
                    response = await tareaService.searchAll(this.userId, searchKeyword);
                } else {
                    response = await tareaService.searchStatusOnly(this.status, searchKeyword, this.userId);
                }
                this.tareas = response.data;
            } catch (error) {
                console.error('Error al obtener las tareas:', error);
            }
        },
        actualizarStatus() {
            if (this.filtroSeleccionado === 'pendientes') {
                this.status = false;
            } else if (this.filtroSeleccionado === 'completadas') {
                this.status = true;
            } else {
                this.obtenerTareas();
            }
        }
    },
    mounted() {
        this.obtenerTareas();
    }
};
</script>

<style>
.container {
    display: flex;
    justify-content: right;
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

.tabla-tareas th {
    background-color: #2c3e50;
    color: white;
    font-weight: bold;
    padding: 10px;
}

.left-box {
    border-top-left-radius: 10px;
    border-bottom-left-radius: 10px;
}

.right-box {
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;
}

.elem-tabla {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: center;
}

.container-filtro {
    width: 20%;
    margin: 0 10px;
    background-color: #386ca0;
    border-radius: 10px;
}

.barra-busqueda {
    text-align: center;
    margin: 20px;
}

.boton-buscar-container {
    text-align: center;
    margin: 20px;
}

.check-tareas {
    margin: 0 20px 10px 20px;
}

.busqueda-input {
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    width: 80%;
}

.busqueda-boton {
    padding: 10px;
    background-color: #2c3e50;
    color: white;
    border: 1px solid #2c3e50;
    border-radius: 5px;
    width: 80%;
}

h1 {
    text-align: center;
    margin: 20px;
    font-size: 25px;
    font-family: Arial, sans-serif;
}

.boton-revisar {
    padding: 10px;
    background-color: #386ca0;
    color: white;
    border: 1px solid #386ca0;
    border-radius: 5px;
}

.boton-revisar:hover {
    background-color: #34495e;
}
.boton-revisar:active {
    background-color: #1c2a39;
    transform: scale(0.98);
    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
}

.busqueda-boton:hover{
    background-color: #34495e;
}
.busqueda-boton:active{
    background-color: #1c2a39;
    transform: scale(0.98);
    box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.2);
}
</style>

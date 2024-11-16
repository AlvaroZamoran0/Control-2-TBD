import httpClient from "../http-common";

const crearTarea = (data) => {
    return httpClient.post('/tarea/crear', data);
};

const getAllTareas = () => {
    return httpClient.get('/tarea/All');
};

const getTareasByUser = (id) => {
    return httpClient.get(`/tarea/user/${id}`);
};

const getTareasById = (id) => {
    return httpClient.get(`/tarea/get/${id}`);
};

const updateTarea = (id, data) => {
    return httpClient.put(`/tarea/update/${id}`, data);
};

const deleteTarea = (id) => {
    return httpClient.delete(`/tarea/delete/${id}`);
};

const searchStatusOnly = (status, keyword, idUser) => {
    return httpClient.get(`/tarea/search/${status}/${keyword}/${idUser}`);
}

export default {
    crearTarea,
    getAllTareas,
    getTareasByUser,
    getTareasById,
    updateTarea,
    deleteTarea,
    searchStatusOnly };
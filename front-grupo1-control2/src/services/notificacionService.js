import httpClient from "../http-common";

const createNotificacion = (notificacion) => {
    return httpClient.post("/notificaciones/crear", notificacion);
};

const getNotificacionesByUser = (idUser) => {
    return httpClient.get(`/notificaciones/user/${idUser}`);
};

const getNotificacionesByUserAND = (idUser) => {
    return httpClient.get(`/notificaciones/userAND/${idUser}`);
};

const marcarTodasLeidas = (idUser) => {
    return httpClient.put(`/notificacion/marcarTodasLeidas/${idUser}`);
};

const checkTareasVencidas = (idUser) => {
    return httpClient.post(`/notificaciones/checkTareasAVencer/${idUser}`);
};

const checkTarea = (idNotificacion) => {
    return httpClient.put(`/notificaciones/marcarLeida/${idNotificacion}`);
};

export default {
    createNotificacion,
    getNotificacionesByUser,
    getNotificacionesByUserAND,
    marcarTodasLeidas,
    checkTareasVencidas,
    checkTarea
};

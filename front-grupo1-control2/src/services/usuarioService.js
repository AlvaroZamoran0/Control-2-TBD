import httpClient from "../http-common";

const createUser = (usuario) => {
    return httpClient.post("/register", usuario);
};

const login = (name, password) => {
    return httpClient.post("/login", {
      username: name, 
      contrasena: password,
    });
};

const getAllUsers = () => {
    return httpClient.get("/usuario/all");
}

const getUser = (id) => {
    return httpClient.get(`/usuario/${id}`);
}

const updateUser = (id, usuario) => {
    return httpClient.put(`/usuario/update/${id}`, usuario);
};

const deleteUser = (id) => {
    return httpClient.delete(`/usuario/delete/${id}`);
};

const searchByCorreo = (correo) => {
    return httpClient.get(`/usuario/search/correo/${correo}`);
};

export default {
    login,
    createUser,
    getAllUsers,
    getUser,
    updateUser,
    deleteUser,
    searchByCorreo,
};
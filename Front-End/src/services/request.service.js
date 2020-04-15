const axios = require('axios');
//const SERVER_URL = 'http://savesforsales-back.herokuapp.com';
const SERVER_URL = 'http://localhost:8083';

const REQUEST_TYPES = {
    GET: 'GET',
    POST: 'POST',
};

function generalRequest(path, body, requestType, callback) {

    var promise;
    switch (requestType) {
        case REQUEST_TYPES.GET:
            promise = axios.get(SERVER_URL + path);
            break;
        case REQUEST_TYPES.POST:
            promise = axios.post(SERVER_URL + path, body);
            break;
    }

    promise.then((result) => {
        callback(result.data);
    }).catch((err) => {
        console.log('error occurred on HTTP request');
        console.log(err);
        callback({ ok: false, msg: 'An error occurred while sending info', err });
    });
}

function loginUsuario(correo, password, callback) {
    generalRequest('/usuario/login', { correo, password }, REQUEST_TYPES.POST, callback);
}

function crearUsuario(nombre, correo, password, callback) {
    generalRequest('/usuario/crear', { nombre, correo, password }, REQUEST_TYPES.POST, callback);
}

module.exports = {
    SERVER_URL,
    REQUEST_TYPES,
    generalRequest,
    loginUsuario,
    crearUsuario,
}
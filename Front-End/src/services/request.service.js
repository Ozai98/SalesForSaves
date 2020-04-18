//const SERVER_URL = 'http://savesforsales-back.herokuapp.com';
const SERVER_URL = "http://localhost:8083";

const REQUEST_TYPES = {
    GET: "GET",
    POST: "POST"
};

function generalRequest(path, body, requestType, callback) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

    var urlencoded = new URLSearchParams();

    for (const key in body) urlencoded.append(key, body[key]);

    var requestOptions = {
        method: requestType,
        headers: myHeaders,
        body: urlencoded,
        redirect: "follow"
    };

    fetch(SERVER_URL + path, requestOptions)
        .then(response => response.json())
        .then(result => callback(result))
        .catch(error => {
            console.log(error);
            callback({
                ok: false,
                msg: "An error ocurred while sending HTTP request"
            });
        });
}

function loginUsuario(correo, password, callback) {
    generalRequest(
        "/usuario/login", { correo, password },
        REQUEST_TYPES.POST,
        callback
    );
}

function crearUsuario(nombre, correo, password, avatar, callback) {
    generalRequest("/usuario/crear", { nombre, correo, password, avatar }, REQUEST_TYPES.POST, callback);
}

function getUserByEmail(correo, callback) {
    generalRequest(
        "/usuario/get-by-email", { correo },
        REQUEST_TYPES.POST,
        callback
    );
}

function getUserById(id, callback) {
    generalRequest(
        "/usuario/get-by-id/" + id,
        undefined,
        REQUEST_TYPES.GET,
        callback
    );
}

// Los parametros que recive son opcionales. Solo envien los valores a actualizar
function updateUser(id, nombre, password, callback) {
    generalRequest(
        "/usuario/update", { id, nombre, password },
        REQUEST_TYPES.POST,
        callback
    );
}

module.exports = {
    SERVER_URL,
    REQUEST_TYPES,
    generalRequest,
    loginUsuario,
    crearUsuario,
    getUserByEmail,
    getUserById,
    updateUser
};
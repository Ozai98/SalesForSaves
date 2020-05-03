//const SERVER_URL = 'http://190.157.224.51:8083/';
const SERVER_URL = "http://localhost:8083";

const REQUEST_TYPES = {
  GET: "GET",
  POST: "POST"
};

function generalRequest(path, body, requestType, callback) {
  var myHeaders = new Headers();
  myHeaders.append("Content-Type", "application/x-www-form-urlencoded");

  var urlencoded = undefined;
  if (requestType != REQUEST_TYPES.GET) {
    urlencoded = new URLSearchParams();
    for (const key in body) urlencoded.append(key, body[key]);
  }

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

//-------------------------------------------------------------------
//----------------------USUARIO--------------------------------------
//-------------------------------------------------------------------

function loginUsuario(correo, password, callback) {
  generalRequest(
    "/usuario/login",
    { correo, password },
    REQUEST_TYPES.POST,
    callback
  );
}

function crearUsuario(nombre, correo, password, avatar, callback) {
  generalRequest(
    "/usuario/crear",
    { nombre, correo, password, avatar },
    REQUEST_TYPES.POST,
    callback
  );
}

function getUserByEmail(correo, callback) {
  generalRequest(
    "/usuario/get-by-email",
    { correo },
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
function updateUser(id, nombre, password, avatar, callback) {
  var body = { id };
  if (nombre) body.nombre = nombre;
  if (password) body.password = password;
  if (avatar) body.avatar = avatar;

  generalRequest("/usuario/update", body, REQUEST_TYPES.POST, callback);
}

//-------------------------------------------------------------------
//----------------------PROVEEDOR------------------------------------
//-------------------------------------------------------------------

function loginProveedor(correo, password, callback) {
  generalRequest(
    "/proveedor/login",
    { correo, password },
    REQUEST_TYPES.POST,
    callback
  );
}

function crearProveedor(nombre, correo, password, avatar, callback) {
  generalRequest(
    "/proveedor/crear",
    { nombre, correo, password, avatar },
    REQUEST_TYPES.POST,
    callback
  );
}

function getProveedorById(id, callback) {
  generalRequest(
    "/proveedor/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

// Los parametros que recive son opcionales. Solo envien los valores a actualizar
function updateProveedor(id, nombre, password, ubicacion, avatar, callback) {
  var body = { id };
  if (nombre) body.nombre = nombre;
  if (password) body.password = password;
  if (avatar) body.avatar = avatar;
  if (ubicacion) body.ubicacion = ubicacion;
  generalRequest("/proveedor/update", body, REQUEST_TYPES.POST, callback);
}

//-------------------------------------------------------------------
//----------------------PRODUCTO-------------------------------------
//-------------------------------------------------------------------

// Parametro es una cadena que se desesa buscar
function buscarProducto(parametro, callback) {
  generalRequest(
    "/producto/search/" + parametro,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}
function getHistoricbyId(id , callback) {
  generalRequest("historico/buyed/"+ id,
  undefined,
  REQUEST_TYPES.GET,
  callback);
}
function getProductoById(id, callback) {
  generalRequest(
    "/producto/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

// El parametro imagen es opcional. Pueden pasar undefined o null
function crearProducto(
  nombre,
  precio,
  cantidad,
  id_proveedor,
  imagen,
  callback
) {
  generalRequest(
    "/producto/crear",
    { nombre, precio, cantidad, id_proveedor, imagen },
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
  updateUser,
  loginProveedor,
  crearProveedor,
  getProveedorById,
  updateProveedor,
  buscarProducto,
  getProductoById,
  crearProducto
};

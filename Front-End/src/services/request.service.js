//const SERVER_URL = 'http://190.157.224.51:8083/';
const SERVER_URL = "http://localhost:8083";

const REQUEST_TYPES = {
  GET: "GET",
  POST: "POST",
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
    redirect: "follow",
  };

  fetch(SERVER_URL + path, requestOptions)
    .then((response) => response.json())
    .then((result) => callback(result))
    .catch((error) => {
      console.log(error);
      callback({
        ok: false,
        msg: "An error ocurred while sending HTTP request",
      });
    });
}

//-------------------------------------------------------------------
//----------------------USUARIO--------------------------------------
//-------------------------------------------------------------------

function loginClient(mail, password, callback) {
  generalRequest(
    "/user/login",
    { mail, password },
    REQUEST_TYPES.POST,
    callback
  );
}

function createClient(name, mail, password, avatar, callback) {
  generalRequest(
    "/user/create",
    { name, mail, password, avatar },
    REQUEST_TYPES.POST,
    callback
  );
}

function getUserByEmail(mail, callback) {
  generalRequest("/user/get-by-email", { mail }, REQUEST_TYPES.POST, callback);
}

function getUserById(id, callback) {
  generalRequest(
    "/user/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

// Los parametros que recive son opcionales. Solo envien los valores a actualizar
function updateClient(id, name, password, avatar, callback) {
  var body = { id };
  if (name) body.name = name;
  if (password) body.password = password;
  if (avatar) body.avatar = avatar;

  generalRequest("/user/update", body, REQUEST_TYPES.POST, callback);
}

//-------------------------------------------------------------------
//----------------------PROVEEDOR------------------------------------
//-------------------------------------------------------------------

function loginProvider(mail, password, callback) {
  generalRequest(
    "/provider/login",
    { mail, password },
    REQUEST_TYPES.POST,
    callback
  );
}

function createProvider(name, mail, password, avatar, callback) {
  generalRequest(
    "/provider/create",
    { name, mail, password, avatar },
    REQUEST_TYPES.POST,
    callback
  );
}

function getProviderById(id, callback) {
  generalRequest(
    "/provider/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

// Los parametros que recive son opcionales. Solo envien los valores a actualizar
function updateProvider(id, name, password, ubication, avatar, callback) {
  var body = { id };
  if (name) body.name = name;
  if (password) body.password = password;
  if (avatar) body.avatar = avatar;
  if (ubication) body.ubication = ubicacion;
  generalRequest("/proveedor/update", body, REQUEST_TYPES.POST, callback);
}

//-------------------------------------------------------------------
//----------------------PRODUCTO-------------------------------------
//-------------------------------------------------------------------

// Parametro es una cadena que se desesa buscar
function searchProduct(param, callback) {
  generalRequest(
    "/product/search/" + param,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

function getProductoById(id, callback) {
  generalRequest(
    "/product/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    callback
  );
}

// El parametro imagen es opcional. Pueden pasar undefined o null
function createProduct(name, price, quantity, id_provider, picture, callback) {
  generalRequest(
    "/producto/crear",
    { name, price, quantity, id_provider, picture },
    REQUEST_TYPES.POST,
    callback
  );
}
//-------------------------------------------------------------------
//----------------------HISTORICO-------------------------------------
//-------------------------------------------------------------------
function getHistoricbyId(idUser, callback) {
  generalRequest(
    "/historic/get-user-reserved",
    {idUser},
    REQUEST_TYPES.POST,
    callback
  );
}

function newReserve(idUser, idProduct, quantity, callback) {
  generalRequest(
    "/historic/reserve/",
    { idUser, idProduct, quantity, reserveDate: new Date() },
    REQUEST_TYPES.POST,
    callback
  );
}

module.exports = {
  SERVER_URL,
  REQUEST_TYPES,
  generalRequest,
  loginClient,
  createClient,
  getUserByEmail,
  getUserById,
  updateClient,
  loginProvider,
  createProvider,
  getProviderById,
  updateProvider,
  searchProduct,
  getProductoById,
  createProduct,
  newReserve,
  getHistoricbyId,
};

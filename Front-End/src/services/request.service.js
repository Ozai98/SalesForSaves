//const SERVER_URL = "http://190.157.224.51:8083/";
const SERVER_URL = "http://localhost:8083";

const REQUEST_TYPES = {
  GET: "GET",
  POST: "POST",
};

function generalRequest(path, body, requestType, useFormData, callback) {

  var formdata = new FormData();
  formdata.append("name", "Test User");
  formdata.append("mail", "testmail@testmail.com");
  formdata.append("password", "0000");

  var requestOptions = {
    method: 'POST',
    body: formdata,
    redirect: 'follow'
  };

  fetch("localhost:8083/user/create", requestOptions)
    .then((response) => response.json())
    .then((result) => callback(result))
    .catch((error) => {
      console.log(error);
      callback({
        ok: false,
        msg: {
          msg: "An error occurred while sending http request",
          error,
        },
      });
    });

  // var body = undefined;
  // const myHeaders = new Headers();
  
  // if (requestType != REQUEST_TYPES.GET) {
  //   if (useFormData) {
  //     myHeaders.append("Content-Type", "multipart/form-data; boundary=---------------------------974767299852498929531610575");
  //     body = new FormData();
  //   } else {
  //     myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
  //     body = new URLSearchParams(); 
  //   }
    
  //   for (const key in body) body.append(key, body[key]);
  // }

  // var requestOptions = {
  //   method: requestType,
  //   body: body,
  //   redirect: 'follow',
  //   headers: myHeaders,
  // };

  // console.log(body);

  // fetch(SERVER_URL + path, requestOptions)
  //   .then((response) => response.json())
  //   .then((result) => callback(result))
  //   .catch((error) => {
  //     console.log(error);
  //     callback({
  //       ok: false,
  //       msg: {
  //         msg: "An error occurred while sending http request",
  //         error,
  //       },
  //     });
  //   });
}

//-------------------------------------------------------------------
//----------------------USUARIO--------------------------------------
//-------------------------------------------------------------------

function loginClient(mail, password, callback) {
  generalRequest(
    "/user/login",
    { mail, password },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function createClient(name, mail, password, avatar, callback) {
  generalRequest(
    "/user/create",
    { name, mail, password, avatar },
    REQUEST_TYPES.POST,
    true,
    callback
  );
}

function getUserByEmail(mail, callback) {
  generalRequest(
    "/user/get-by-email",
    { mail },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getUserById(id, callback) {
  generalRequest(
    "/user/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    undefined,
    callback
  );
}

// Los parametros que recive son opcionales. Solo envien los valores a actualizar
function updateClient(id, name, password, avatar, callback) {
  var body = { id };
  if (name) body.name = name;
  if (password) body.password = password;
  if (avatar) body.avatar = avatar;

  generalRequest("/user/update", body, REQUEST_TYPES.POST, undefined, callback);
}

//-------------------------------------------------------------------
//----------------------PROVEEDOR------------------------------------
//-------------------------------------------------------------------

function loginProvider(mail, password, callback) {
  generalRequest(
    "/provider/login",
    { mail, password },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function createProvider(name, mail, password, avatar, callback) {
  generalRequest(
    "/provider/create",
    { name, mail, password, avatar },
    REQUEST_TYPES.POST,
    true,
    callback
  );
}

function getProviderById(id, callback) {
  generalRequest(
    "/provider/get-by-id" + id,
    undefined,
    REQUEST_TYPES.GET,
    undefined,
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
  generalRequest("/proveedor/update", body, REQUEST_TYPES.POST, true, callback);
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
    undefined,
    callback
  );
}

function getProductById(id, callback) {
  generalRequest(
    "/product/get-by-id/" + id,
    undefined,
    REQUEST_TYPES.GET,
    undefined,
    callback
  );
}

// El parametro imagen es opcional. Pueden pasar undefined o null
function createProduct(
  name,
  price,
  quantity,
  idProvider,
  image,
  timeLimit,
  category,
  callback
) {
  generalRequest(
    "/product/create",
    { name, price, quantity, idProvider, image, timeLimit, category },
    REQUEST_TYPES.POST,
    true,
    callback
  );
}

function getByCategory(category, callback) {
  generalRequest(
    "/product/search-category/" + category,
    undefined,
    REQUEST_TYPES.GET,
    undefined,
    callback
  );
}

//-------------------------------------------------------------------
//----------------------HISTORICO-------------------------------------
//-------------------------------------------------------------------
function getHistoricbyId(idUser, callback) {
  generalRequest(
    "/historic/get-user-reserved",
    { idUser },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function newReserve(idUser, idProduct, quantity, callback) {
  generalRequest(
    "/historic/reserve",
    {
      idUser,
      idProduct,
      quantity,
      reserveDate: new Date(new Date().getTime() + 1000 * 60 * 60 * 24 * 10),
    },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function newPurchase(idHistoric, callback) {
  generalRequest(
    "/historic/buyed-product",
    { idHistoric },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getReservedUser(idUser, callback) {
  generalRequest(
    "/historic/get-user-reserved",
    { idUser },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getPurchasedUser(idUser, callback) {
  generalRequest(
    "/historic/get-user-historic",
    { idUser },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getReservedProv(idProv, callback) {
  generalRequest(
    "/historic/get-provider-historic",
    { idProv },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getPurchasedProv(idProv, callback) {
  generalRequest(
    "/historic/get-provider-historic",
    { idProv },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

//-------------------------------------------------------------------
//----------------------RATE-----------------------------------------
//-------------------------------------------------------------------

function addRate(idProvider, idUser, rate, callback) {
  generalRequest(
    "rate/add-rate/" + rate,
    { idProvider, idUser },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

function getRate(idProvider, callback) {
  generalRequest(
    "rate/rating",
    { idProvider },
    REQUEST_TYPES.POST,
    undefined,
    callback
  );
}

//-------------------------------------------------------------------
//----------------------GENERAL--------------------------------------
//-------------------------------------------------------------------
function getCategories(callback) {
  callback({
    ok: true,
    classX: [
      "FRUTAS",
      "VERDURAS",
      "RES",
      "POLLO",
      "CERDO",
      "PESCADO",
      "GRANOS",
      "POSTRES",
      "BEBIDAS",
      "HARINAS",
    ],
    msg: "Ok",
  });
}

function getImgUrl(imgName) {
  return SERVER_URL + "/general/get-image/" + imgName;
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
  getProductById,
  createProduct,
  getHistoricbyId,
  newReserve,
  getByCategory,
  getCategories,
  getImgUrl,
};

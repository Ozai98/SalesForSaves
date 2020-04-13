const axios = require('axios').default;

const URL =   'http://savesforsales-back.herokuapp.com';

function makeRequest(path, body) {
    console.log('A console.log in requestService');
    if (body) return axios.post(URL + path, body);
    else return axios.get(URL + path);
}

module.exports = {
    makeRequest,
    URL,
};
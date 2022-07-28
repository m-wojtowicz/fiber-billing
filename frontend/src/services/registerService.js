import axios from "axios";
import { registerStore } from "../stores/register";

const register = registerStore();
const url = "http://localhost:8000/api";

export const registerKeycloak = async () => {
  let params = {
    grant_type: "client_credentials",
    client_id: "admin-cli",
    client_secret: "kGNEDI8CQphsw42oSzPk6Y1vVByAVmVo",
  };

  let access_token = "";

  let data = Object.keys(params)
    .map((key) => `${key}=${encodeURIComponent(params[key])}`)
    .join("&");

  let options = {
    method: "POST",
    headers: { "content-type": "application/x-www-form-urlencoded" },
    data,
    url: "http://localhost:8080/auth/realms/master/protocol/openid-connect/token",
  };

  await axios(options).then((r) => (access_token = r.data.access_token));

  data = {
    firstName: register.name,
    lastName: register.surname,
    email: register.email,
    enabled: true,
    username: register.login,
    credentials: [
      {
        type: "password",
        value: register.password,
        temporary: false,
      },
    ],
  };

  options = {
    method: "POST",
    headers: {
      "content-type": "application/json",
      Authorization: `Bearer ${access_token}`,
    },
    data,
    url: "http://localhost:8080/auth/admin/realms/fiber-billing/users",
  };

  return axios(options);
};

export const registerUser = async () => {
  let clientType = {};

  await axios
    .get(url + `/client-type/name/${register.clientType}`)
    .then((r) => (clientType = r.data));

  let userData = {
    login: register.login,
    clientType: clientType,
  };

  return axios.post(url + "/user", userData).catch((err) => console.log(err));
};

export const registerAddress = async () => {
  let addressData = {
    street: register.street,
    houseNumber: register.streetNumber,
    apartmentNumber: register.houseNumber,
    zipCode: register.zipCode,
    postOffice: register.postOffice,
    city: register.city,
  };

  return axios.post(url + "/address", addressData);
};

export const registerClientData = async (user, address) => {
  let clientDataData = {
    name: register.name,
    surname: register.surname,
    emailAddress: register.email,
    address: address,
    user: user,
  };

  return axios.post(url + "/client-data", clientDataData);
};

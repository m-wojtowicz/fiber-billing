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

export const registerAddress = async () => {
  let addressData = {
    country: register.country,
    city: register.city,
    street: register.street,
    streetNumber: register.streetNumber,
    houseNumber: register.houseNumber,
    zipCode: register.zipCode,
    postOffice: register.postOffice,
  };

  return axios.post(url + "/address", addressData);
};

export const registerClientData = async (addressId, clientTypeId) => {
  let clientDataData = {
    name: register.name,
    surname: register.surname,
    emailAddress: register.email,
    address: {
      id: addressId,
    },
    login: register.login,
    clientType: {
      id: clientTypeId,
    },
  };

  return axios.post(url + "/client-data", clientDataData);
};

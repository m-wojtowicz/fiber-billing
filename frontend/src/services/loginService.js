import axios from "axios";

export const getLoginToken = async (login, password) => {
  let params = {
    grant_type: "password",
    username: login,
    password: password,
    client_id: "fiber-billing",
  };

  const data = Object.keys(params)
    .map((key) => `${key}=${encodeURIComponent(params[key])}`)
    .join("&");

  const options = {
    method: "POST",
    headers: { "content-type": "application/x-www-form-urlencoded" },
    data,
    url: "http://localhost:8080/auth/realms/fiber-billing/protocol/openid-connect/token",
  };

  return await axios(options);
};

export const exitSession = async (token) => {
  let params = {
    client_id: "fiber-billing",
    refresh_token: token.refresh_token,
  };

  let data = Object.keys(params)
    .map((key) => `${key}=${encodeURIComponent(params[key])}`)
    .join("&");

  const options = {
    method: "POST",
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
    data,
    url: "http://localhost:8080/auth/realms/fiber-billing/protocol/openid-connect/logout",
  };

  return await axios(options);
};

export const refreshToken = async (token) => {
  let params = {
    client_id: "fiber-billing",
    grant_type: "refresh_token",
    refresh_token: token.refresh_token,
  };

  let data = Object.keys(params)
    .map((key) => `${key}=${encodeURIComponent(params[key])}`)
    .join("&");

  const options = {
    method: "POST",
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
    data,
    url: "http://localhost:8080/auth/realms/fiber-billing/protocol/openid-connect/token",
  };

  return await axios(options);
};

export const getUserType = async (login) => {
  let url = `http://localhost:8000/api/client-type/login/${login}`;
  return await axios.get(url);
};

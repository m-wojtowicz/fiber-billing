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

import axios from "axios";

export const getUserData = async (login) => {
  const url = `http://localhost:8000/api/client-data/user/${login}`;
  return await axios.get(url);
};

export const putUserData = async (user) => {
  let clientDataId = user.id;
  let userData = {
    id: user.id,
    name: user.name,
    surname: user.surname,
    email: user.email,
    country: user.country,
    city: user.city,
    street: user.street,
    streetNumber: user.streetNumber,
    houseNumber: user.houseNumber,
    zipCode: user.zipCode,
    postOffice: user.postOffice,
  };
  const url = `http://localhost:8000/api/client-data/${clientDataId}`;
  return await axios.put(url, userData);
};

export const getClientType = async (login) => {
  const url = `http://localhost:8000/api/client-type/login/${login}`;
  return await axios.get(url);
};

import axios from "axios";

export const getUserData = async (login) => {
  const url = `http://localhost:8000/api/client-data/user/${login}`;
  console.log(login);
  return await axios.get(url);
};

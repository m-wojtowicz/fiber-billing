import axios from "axios";

export const getAllProducts = async (id, userType) => {
  const url = `http://localhost:8000/api/orderItem/all/${id}`;
  return await axios.get(url, { params: { userType: userType } });
};

export const getAllProductParameters = async (id) => {
  const url = `http://localhost:8000/api/orderItem/parameters/${id}`;
  return await axios.get(url).then((resp) => {
    return resp.data;
  });
};

import axios from "axios";

export const getAllProducts = async (id) => {
  const url = `http://localhost:8000/api/orderItem/all/${id}`;
  return await axios.get(url);
};

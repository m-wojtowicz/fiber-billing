import axios from "axios";

export const getUserOrders = async (login) => {
  let url = `http://localhost:8000/api/client-data/user/${login}`;
  const user = await (await axios.get(url)).data;
  const userId = user.id;

  url = `http://localhost:8000/api/order/user/${userId}`;
  const orders = await (await axios.get(url)).data;
  return orders;
};

export const getOrderItems = async (orderId) => {
  let url = `http://localhost:8000/api/order/${orderId}/items`
  const items = await (await axios.get(url)).data;
  return items;
}

export const getItemParameters = async (itemId) => {
  let url = `http://localhost:8000/api/order-item/${itemId}/parameters`
  const parameters = await (await axios.get(url)).data;
  return parameters;
}
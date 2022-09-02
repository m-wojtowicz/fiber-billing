import axios from "axios";

export const getUserOrders = async (login, pageNo, filter) => {
  let url = `http://localhost:8000/api/client-data/user/${login}`;
  const user = await (await axios.get(url)).data;
  const userId = user.id;

  url = `http://localhost:8000/api/order/user/${userId}`;
  const orders = await (
    await axios.get(url, {
      params: {
        pageNo: pageNo-1,
        filter: filter,
      },
    })
  ).data;
  return orders;
};

export const getOrderItems = async (orderId) => {
  let url = `http://localhost:8000/api/order/${orderId}/items`;
  const items = await await axios.get(url);
  return items;
};

export const getItemParameters = async (itemId) => {
  let url = `http://localhost:8000/api/orderItem/parameters/${itemId}`;
  const parameters = await (await axios.get(url)).data;
  return parameters;
};

export const checkOpenOrder = async (userId) => {
  let url = `http://localhost:8000/api/order/user/${userId}/open`;
  return await axios.get(url);
};

export const createOrder = async (userId) => {
  let url = `http://localhost:8000/api/order/user/${userId}`;
  return await axios.post(url);
};

export const addOfferToOrder = async (orderId, offerId) => {
  let url = `http://localhost:8000/api/orderItem/${orderId}/offer`;
  return await axios.post(url, offerId, {
    headers: { "Content-Type": "application/json" },
  });
};

export const removeItem = async (itemId) => {
  let url = `http://localhost:8000/api/orderItem/${itemId}`;
  return await axios.delete(url);
};

import axios from "axios";

export const updateOrderStatus = async (orderId) => {
  let url = `http://localhost:8000/api/order/${orderId}/status`;
  return await axios.put(url, "Pending", {
    headers: { "Content-Type": "text/plain" },
  });
};

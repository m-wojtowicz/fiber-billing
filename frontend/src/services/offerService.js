import axios from "axios";

export const getAvailableOffers = async () => {
  let url = `http://localhost:8000/api/offer/all`;
  return await axios.get(url);
};

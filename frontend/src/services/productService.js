import axios from "axios";

export const getUserProducts = async (login, pageNo, filter, filterType) => {
  let url = `http://localhost:8000/api/client-data/user/${login}`;
  const user = await (await axios.get(url)).data;
  const userId = user.id;

  url = `http://localhost:8000/api/orderItem/user/${userId}`;
  const products = await (
    await axios.get(url, {
      params: {
        pageNo: pageNo - 1,
        filter: filter,
        filterType: filterType,
      },
    })
  ).data;
  return products;
};

export const getAllProductParameters = async (id) => {
  const url = `http://localhost:8000/api/orderItem/parameters/${id}`;
  return await axios.get(url).then((resp) => {
    return resp.data;
  });
};

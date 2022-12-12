import axios from "axios";

export const getUserInvoices = async (login, pageNo, rowsCount) => {
  let url = `http://localhost:8000/api/client-data/user/${login}`;
  const user = await (await axios.get(url)).data;
  const userId = user.id;

  url = `http://localhost:8000/api/invoice/user/${userId}`;
  const invoices = await (
    await axios.get(url, {
      params: {
        pageNo: pageNo - 1,
        rowsCount: rowsCount
      },
    })
  ).data;
  return invoices;
};
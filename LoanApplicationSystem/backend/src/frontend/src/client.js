import fetch from 'unfetch';

const checkStatus = response => {
    if (response.ok) {
        return response;
    }
    // convert non-2xx HTTP responses into errors:
    const error = new Error(response.statusText);
    error.response = response;
    return Promise.reject(error);
}

export const getAllCustomers = () =>
    fetch("localhost:8080/api/v1/customers")
        .then(checkStatus);

export const addNewCustomer = customer =>
    fetch("api/v1/customers", {
            headers: {
                'Content-Type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify(customer)
        }
    ).then(checkStatus)

export const deleteCustomer = customerId =>
    fetch(`api/v1/customers/${customerId}`, {
        method: 'DELETE'
    }).then(checkStatus);
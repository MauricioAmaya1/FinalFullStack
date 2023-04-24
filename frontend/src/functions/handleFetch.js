import Swal from "sweetalert2";

function loadingMessage() {
    Swal.fire({
        title: "Espere un momento",
        timerProgressBar: true,
        didOpen: () => {
            Swal.showLoading()
        }
    })
}

export function handleFetchGet(url, setData, setLoading, emptyAlert, failedAlert, uploadToSessionStorage) {

    const abortController = new AbortController()

    if (setLoading) {
        setLoading(true)
    }
    fetch(url)
        .then((res) => {

            if (res.status === 200 || res.status === 202) {
                return res.json()

            } else if (res.status === 404) {
                if (emptyAlert) {
                    emptyAlert()
                }

            } else {
                if (failedAlert) {
                    failedAlert()
                }
            }
        }).then((response) => {
            if (setData) {
                setData(response)
            }

            if (uploadToSessionStorage) {
                uploadToSessionStorage(response)
            }

            if (setLoading) {
                setLoading(false)
            }
        }).catch((error) => console.error(error))

    return () => abortController.abort()
}


export function handleFetchPost(url, data, headers, showLoading, succesMessage, errorMessage403, errorMessage, succes, uploadToSessionStorage) {

    let loading = true

    const abortController = new AbortController()

    if (showLoading) {
        if (loading) {
            loadingMessage()
        }
    }

    fetch(url, {
        method: "POST",
        body: JSON.stringify(data),
        headers: headers
    }).then((res) => {

        if (res.status === 200) {
            return res.json()

        } else if (res.status === 201) {
            if (succesMessage) {
                if (showLoading) {
                    loading = false
                }
                succesMessage()
            }

        } else if (res.status === 403) {
            if (errorMessage403) {
                errorMessage403()
            }

        } else {
            if (errorMessage) {
                errorMessage()
            }
        }
    }).then(response => {
        if (response) {
            if (succes) {
                succes()
                if (uploadToSessionStorage) {
                    uploadToSessionStorage(response)
                }
            }
        }
    }).catch((error) => console.error(error))

    return () => abortController.abort()
}


export function handleFetchPut(url, data, headers, showLoading, succesMessage, errorMessage) {

    let loading = true

    const abortController = new AbortController()

    if (showLoading) {
        if (loading) {
            loadingMessage()
        }
    }

    fetch(url, {
        method: 'PUT',
        body: JSON.stringify(data),
        headers: headers
    }).then(res => {

        if (res.status === 202) {
            if (succesMessage) {
                succesMessage()
            }

        } else {
            if (errorMessage) {
                errorMessage()
            }
        }

    }).catch(error => console.error(error))

    return () => abortController.abort()
}


export function handleFetchDelete(url, headers, showLoading, succesMessage, errorMessage) {

    let loading = true

    const abortController = new AbortController()

    if (showLoading) {
        if (loading) {
            loadingMessage()
        }
    }

    fetch(url, {
        method: 'DELETE',
        headers: headers
    }).then(res => {

        if (res.status === 200) {
            if (showLoading) {
                loading = false
            }
            if (succesMessage) {
                succesMessage()
            }

        } else {
            if (errorMessage) {
                errorMessage()
            }
        }
    }).catch(error => console.error(error))

    return () => abortController.abort()
}
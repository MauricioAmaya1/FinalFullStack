import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import Template from '../ui/template/Template';

import routes from './Routes';

const router = createBrowserRouter([
    {
        path: '/',
        element: <Template />,
        errorElement: <div>Error!!!</div>,
        children: routes.map(({path, Element}) => {
            return {
                path: path,
                element: <Element />
            }
        })
    }
])

const Navigation = () => <RouterProvider router={router} />;

export default Navigation;
import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.scss';
import App from './App';
import BookShelf from './components/bookshelf'
import reportWebVitals from './reportWebVitals';
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import ErrorPage from "./components/error-page";
import Logout from "./components/logout";
const router = createBrowserRouter(
    [
        {path:"/", element:<App/>},
        {path:"/bookshelf", element:<BookShelf/>},
        {path:"/logout", element:<Logout/>},
        {path: "/", element: <App />, errorElement: <ErrorPage />,
        },
    ]
)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
      <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

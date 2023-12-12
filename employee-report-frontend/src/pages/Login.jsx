import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Image from "../assets/login1.svg";
import toast from "react-hot-toast";
import { useDispatch } from "react-redux";
import { login } from "../redux/authSlice";
const LoginAdmin = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prevFormData) => ({
      ...prevFormData,
      [id]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (formData.email === "swapnilsahoo100@gmail.com") {
      dispatch(
        login({
          user: {
            role: "admin",
          },
        })
      );
      navigate("/report");
    } else {
      axios
        .post("http://localhost:8080/api/v1/user/login", formData)
        .then((res) => {
          toast.success("Login Success");
          res.data.user.role = "employee";
          dispatch(login({ user: res.data.user }));
          navigate("/form");
        })
        .catch((err) => {
          toast.error("Login Failed");
        });
    }
  };

  return (
    <div className="flex h-[90vh]">
      <a href="/" className="absolute top-5 left-5">
        {/* <ArrowLeft /> */}
      </a>
      <div
        className="w-1/2 bg-center bg-no-repeat"
        style={{ backgroundImage: `url(${Image})` }}
      ></div>
      <div className="w-1/2 flex justify-center items-center">
        <form className="p-2 bg-white w-2/3" onSubmit={handleSubmit}>
          <h2 className="text-2xl font-semibold mb-4">
            <span className="font-bold">Login</span>
          </h2>
          <div className="mb-4">
            <label
              htmlFor="email"
              className="block text-gray-700 text-sm font-bold mb-2"
            >
              Email
            </label>
            <input
              type="email"
              id="email"
              className="w-full border rounded py-2 px-3"
              placeholder="Enter your Email"
              value={formData.email}
              onChange={handleChange}
            />
          </div>
          <div className="mb-4">
            <label
              htmlFor="password"
              className="block text-gray-700 text-sm font-bold mb-2"
            >
              Password
            </label>
            <input
              type="password"
              id="password"
              className="w-full border rounded py-2 px-3"
              placeholder="Enter your Password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>

          <button
            type="submit"
            className="w-full bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded"
          >
            Login
          </button>
          <p className="mt-2">
            Don't have an account?{" "}
            <a href="/register" className="font-semibold">
              Register
            </a>
          </p>
        </form>
      </div>
    </div>
  );
};
export default LoginAdmin;

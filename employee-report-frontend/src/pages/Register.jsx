import React, { useState } from "react";
import Image from "../assets/register1.svg";
import axios from "axios";
import { toast } from "react-hot-toast";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { login } from "../redux/authSlice";
import { DEV_BACKEND_URL } from "../utils/constants";

const Register = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    workStartHour: [9, 0],
    workEndHour: [17, 0],
    manager: "", // New field for manager
  });
  const workHourOptions = Array.from({ length: 24 }, (_, index) => {
    const hour = index % 12 || 12; // Convert to 12-hour format
    const ampm = index < 12 ? "AM" : "PM";
    return { value: [index, 0], label: `${hour}:00 ${ampm}` };
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    if (id === "workStartHour" || id === "workEndHour") {
      const [hour, minute] = value.split(",");
      setFormData((prevFormData) => ({
        ...prevFormData,
        [id]: [Number(hour), Number(minute)],
      }));
      return;
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [id]: value,
      }));
    }
    console.log(formData);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(formData);
    axios
      .post(`${DEV_BACKEND_URL}/user/register`, formData)
      .then((res) => {
        toast.success("Register Success");
        console.log(res.data);
        dispatch(login({ user: res.data }));
        navigate("/form");
      })
      .catch((err) => {
        toast.error("Register Failed");
      });
  };

  return (
    <div className="flex h-[90vh]">
      <a href="/" className="absolute top-5 left-5">
        {/* <ArrowLeft /> */}
      </a>
      <div className="w-1/2 flex justify-center items-center">
        <form className="p-2 bg-white w-2/3" onSubmit={handleSubmit}>
          <h2 className="text-2xl font-bold mb-3">Register</h2>
          <div className="mb-3">
            <label
              htmlFor="firstName"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              First Name
            </label>
            <input
              type="text"
              id="firstName"
              className="w-full border rounded py-2 px-3"
              placeholder="Enter your First Name"
              value={formData.firstName}
              onChange={handleChange}
            />
          </div>

          <div className="mb-3">
            <label
              htmlFor="lastName"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              Last Name
            </label>
            <input
              type="text"
              id="lastName"
              className="w-full border rounded py-2 px-3"
              placeholder="Enter your Last Name"
              value={formData.lastName}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label
              htmlFor="email"
              className="block text-gray-700 text-sm font-bold mb-1"
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
          <div className="mb-3">
            <label
              htmlFor="password"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              Password
            </label>
            <input
              type="text"
              id="password"
              className="w-full border rounded py-2 px-3"
              placeholder="Enter your Password"
              value={formData.password}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label
              htmlFor="manager"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              Manager
            </label>
            <select
              id="manager"
              value={formData.manager}
              onChange={handleChange}
              className="w-full border rounded py-1 px-3"
            >
              <option value="">Select Manager</option>
              <option value="Rafael Leao">Rafael Leao</option>
              <option value="John Wick">John Wick</option>
              <option value="Julian Alvarez">Julian Alvarez</option>
              {/* Add more manager options as needed */}
            </select>
          </div>
          <div className="mb-3">
            <label
              htmlFor="workStartHour"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              Work Start Time
            </label>
            <select
              id="workStartHour"
              value={formData.workStartHour}
              onChange={handleChange}
              className="w-full border rounded py-1 px-3"
            >
              {workHourOptions.map((option) => (
                <option key={option.value} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>

          <div className="mb-3">
            <label
              htmlFor="workEndHour"
              className="block text-gray-700 text-sm font-bold mb-1"
            >
              Work End Time
            </label>
            <select
              id="workEndHour"
              value={formData.workEndHour}
              onChange={handleChange}
              className="w-full border rounded py-1 px-3"
            >
              {workHourOptions.map((option) => (
                <option key={`${option.value[0]}`} value={option.value}>
                  {option.label}
                </option>
              ))}
            </select>
          </div>

          <button
            type="submit"
            className="w-full bg-orange-500 hover:bg-orange-700 text-white font-bold py-2 px-4 rounded"
          >
            Register
          </button>
          <p className="mt-2">
            Already have an account?{" "}
            <a href="/login" className="font-semibold">
              Login
            </a>
          </p>
        </form>
      </div>
      <div
        className="w-1/2 bg-center bg-no-repeat"
        style={{ backgroundImage: `url(${Image})` }}
      ></div>
    </div>
  );
};
export default Register;

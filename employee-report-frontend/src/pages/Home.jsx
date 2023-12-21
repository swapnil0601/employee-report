import React from "react";
import { useSelector } from "react-redux";
const Home = () => {
  const { isAuthenticated, user } = useSelector((state) => {
    return state.auth;
  });
  console.log(isAuthenticated);
  function extractUsername(email) {
    // Extract the username from the email
    const username = email.substring(0, email.indexOf("@"));
    return username;
  }
  const mailHandler = async () => {
    const response = await fetch("http://localhost:8080/api/v1/mail/sendall", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email:user.email,
        name:user.firstName,
      }),
    });
    const responseData = await response.json();
    console.log(responseData);
  }
  return (
    <div class="bg-neutral-100 h-[90.5vh] flex flex-col items-center justify-center text-center text-neutral-700 ">
      <h1 class="mb-4 text-6xl font-semibold">
        {isAuthenticated
          ? `Welcome ${extractUsername(user?.email)}`
          : "Employee Activity Tracker made easy"}
      </h1>
      <h4 class="mb-6 text-xl text-neutral-500 font-semibold">
        track daily productivity hours
      </h4>
      {user.role!=="admin" ? (
        <button
          type="button"
          data-te-ripple-init
          data-te-ripple-color="light"
          className="text-lg ml-2 px-3 py-1 rounded-md bg-orange-500 hover:bg-orange-700 text-white"
        >
          <a href="/register">Sign Up for Free</a>
        </button>
      ) : (
        <button
          type="button"
          data-te-ripple-init
          data-te-ripple-color="light"
          className="text-lg ml-2 px-3 py-1 rounded-md bg-orange-500 hover:bg-orange-700 text-white"
          onClick={mailHandler}
        >
          Send Mail
        </button>
      )}
    </div>
  );
};

export default Home;

import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { Toaster } from "react-hot-toast";
import ActivityForm from "./components/ActivityReportForm";
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import ReportPage from "./pages/Report";
import UnsubmittedForms from "./components/UnsubmittedForms";
function App() {
  // localStorage.removeItem("token");
  // localStorage.removeItem("account");
  return (
    <BrowserRouter>
      <Toaster position="top-right" reverseOrder={false} />
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/form" element={<UnsubmittedForms />} />
        <Route path="/form/:formId" element={<ActivityForm />} />
        <Route path="/report" element={<ReportPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;

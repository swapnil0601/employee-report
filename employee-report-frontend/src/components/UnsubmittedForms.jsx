import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { DEV_BACKEND_URL } from "../utils/constants";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const UnsubmittedForms = () => {
  const navigate = useNavigate();
  const { user } = useSelector((state) => state.auth);
  const [unsubmittedForms, setUnsubmittedForms] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`${DEV_BACKEND_URL}/form/unsubmittedForms/${user.id}`)
      .then((res) => {
        console.log(res.data.data);
        setUnsubmittedForms(res.data.data);
        setLoading(false);
      })
      .catch((err) => {
        console.log(err);
        setLoading(false);
      });
  }, [user.id]);

  return (
    <div className="flex flex-col gap-3 justify-center items-center p-5">
      <h1 className="text-2xl font-bold">Unsubmitted Forms</h1>
      {loading ? (
        <h1>Loading...</h1>
      ) : (
        unsubmittedForms?.map((form) => {
          return (
            <div
              className="flex gap-3 rounded-md shadow-sm shadow-black p-3 hover:bg-gray-100 hover:indent-1 hover:cursor-pointer"
              onClick={() => navigate(`/form/${form.formId}`)}
            >
              <h1>{form.formId}</h1>
              <h2>{form.date}</h2>
            </div>
          );
        })
      )}
    </div>
  );
};

export default UnsubmittedForms;

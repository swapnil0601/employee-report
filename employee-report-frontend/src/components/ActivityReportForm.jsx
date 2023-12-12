import axios from "axios";
import React, { useEffect, useState } from "react";
import { DEV_BACKEND_URL } from "../utils/constants";
import toast from "react-hot-toast";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import { useNavigate, useParams } from "react-router-dom";
import { useSelector } from "react-redux";

const ActivityForm = () => {
  const navigate = useNavigate();
  const { formId } = useParams();
  const { user } = useSelector((state) => state.auth);
  const [activities, setActivities] = useState([]);
  const [chartData, setChartData] = useState({});

  useEffect(() => {
    setChartData(
      activities.map((activity) => ({
        name: activity.category,
        y: parseInt(activity.minutes),
      }))
    );
  }, [activities]);

  const pieOptions = {
    chart: {
      type: "pie",
    },
    title: {
      text: "Activities Proportion",
    },
    series: [
      {
        name: "Activities",
        data: chartData,
      },
    ],
  };

  const handleAddActivity = () => {
    setActivities([...activities, { category: "", minutes: 0 }]);
  };

  const handleActivityChange = (index, event) => {
    const updatedActivities = [...activities];
    updatedActivities[index].category = event.target.value;
    setActivities(updatedActivities);
  };

  const handleRemoveActivity = (indexToRemove) => {
    setActivities((prevActivities) =>
      prevActivities.filter((category, index) => index !== indexToRemove)
    );
  };

  const handleDurationChange = (index, event) => {
    const updatedActivities = [...activities];
    updatedActivities[index].minutes = parseInt(event.target.value);
    setActivities(updatedActivities);
  };
  const formatDuration = (minutes) => {
    return `${Math.floor(minutes / 60)} hours ${minutes % 60} minutes`;
  };
  const handleSubmit = () => {
    const formData = {
      userId: user.id,
      activityList: activities,
    };
    console.log(formData);
    axios
      .post(`http://localhost:8080/api/v1/form/submit/${formId}`, formData)
      .then((res) => {
        toast.success("Entries Added");
        console.log(res.data);
        setActivities([]);
        navigate("/form");
      })
      .catch((err) => {
        toast.error("Entries Addition Failed");
        console.log(err);
      });
  };

  return (
    <div className="flex flex-row justify-center items-center gap-10 p-2">
      <div class="flex flex-col w-full p-2 gap-5 items-center">
        <div className="flex gap-5">
          <button
            class="px-4 py-1 rounded-md bg-black text-white border border-black"
            onClick={handleAddActivity}
          >
            Add
          </button>
          <button
            class="px-4 py-1 rounded-md bg-orange-500 text-white"
            onClick={handleSubmit}
          >
            Submit
          </button>
        </div>
        <div class="flex flex-col gap-3 w-2/3">
          {activities.map((activity, index) => (
            <div
              class="relative flex flex-col border p-6 border-black rounded-md"
              key={index}
            >
              <select
                class="border border-black rounded-md py-1 px-2"
                value={activity.activity}
                onChange={(e) => handleActivityChange(index, e)}
              >
                <option value="" disabled selected>
                  Select an activity
                </option>
                <option value="Meeting">Meeting</option>
                <option value="Coding">Coding</option>
                <option value="Testing">Testing</option>
                <option value="Debugging">Debugging</option>
                <option value="Designing">Designing</option>
                <option value="Documentation">Documentation</option>
              </select>
              <div class="flex items-center mt-2">
                <input
                  type="range"
                  className="w-full flex-1"
                  min={15}
                  max={480} // 8 hours in minutes
                  value={activity.minutes}
                  step={15} // Increment slider value by 15 minutes
                  onChange={(e) => handleDurationChange(index, e)}
                />
                <span class="ml-2 flex-2">
                  {formatDuration(activity.minutes)}
                </span>
                <button
                  className="absolute text-sm top-1 right-1 ml-2 px-1 text-red-500 hover:bg-red-500 rounded-sm hover:text-white"
                  onClick={() => handleRemoveActivity(index)}
                >
                  X
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="flex flex-col gap-5 w-full">
        <div className="flex flex-col items-center">
          <h3 className="font-bold">Total Hours</h3>
          <p>
            {formatDuration(
              activities.reduce(
                (acc, activity) => acc + parseInt(activity.minutes),
                0
              )
            )}
          </p>
        </div>
        <div className="chart-container">
          <HighchartsReact highcharts={Highcharts} options={pieOptions} />
        </div>
      </div>
    </div>
  );
};

export default ActivityForm;

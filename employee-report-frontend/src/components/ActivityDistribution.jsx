import React, { useEffect, useState } from "react";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import axios from "axios";

const ActivityDistribution = () => {
  //   const [chartData, setChartData] = useState([]);

  //   useEffect(() => {
  //     const fetchActivityDistribution = async () => {
  //       try {
  //         const response = await axios.get("/api/reports/activity-distribution");
  //         const formattedData = response.data.map((item) => ({
  //           name: item.activityName,
  //           y: item.totalHours,
  //         }));
  //         setChartData(formattedData);
  //       } catch (error) {
  //         console.error("Error fetching data:", error);
  //       }
  //     };

  //     fetchActivityDistribution();
  //   }, []);

  //   const options = {
  //     chart: {
  //       type: "pie",
  //     },
  //     title: {
  //       text: "Activity Distribution of Hours",
  //     },
  //     series: [
  //       {
  //         name: "Total Hours",
  //         data: chartData,
  //       },
  //     ],
  //   };

  const chartData = [
    { activity: "Meeting", count: 20 },
    { activity: "Coding", count: 35 },
    { activity: "Testing", count: 15 },
    { activity: "Designing", count: 25 },
    // Add more data as needed
  ];

  // Prepare data for Highcharts
  const options = {
    chart: {
      type: "pie",
    },
    title: {
      text: "Activity Distribution",
    },
    series: [
      {
        name: "Activity",
        data: chartData.map((data) => ({ name: data.activity, y: data.count })),
      },
    ],
  };

  return (
    <div className="p-5 flex-1 w-1/2">
      <HighchartsReact highcharts={Highcharts} options={options} />
    </div>
  );
};

export default ActivityDistribution;

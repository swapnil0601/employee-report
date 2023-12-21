import React, { useEffect, useState } from "react";
import Highcharts from "highcharts";
import HighchartsReact from "highcharts-react-official";
import axios from "axios";

const EmployeeHours = () => {
  //   const [chartData, setChartData] = useState([]);

  //   useEffect(() => {
  //     const fetchEmployeeHours = async () => {
  //       try {
  //         const response = await axios.get("/api/reports/employee-hours");
  //         const formattedData = response.data.map((item) => ({
  //           name: item.employeeName,
  //           y: item.totalHours,
  //         }));
  //         setChartData(formattedData);
  //       } catch (error) {
  //         console.error("Error fetching data:", error);
  //       }
  //     };

  //     fetchEmployeeHours();
  //   }, []);
  //   const options = {
  //     chart: {
  //       type: "column",
  //     },
  //     title: {
  //       text: "Total Hours Worked by Employees",
  //     },
  //     xAxis: {
  //       type: "category",
  //       title: {
  //         text: "Employee",
  //       },
  //     },
  //     yAxis: {
  //       title: {
  //         text: "Total Hours",
  //       },
  //     },
  //     series: [
  //       {
  //         name: "Total Hours",
  //         data: chartData,
  //       },
  //     ],
  //   };
  const chartData = [
    { employee: "Swapnil Sahoo", hours: 17.5 },
    // Add more data as needed
  ];

  // Prepare data for Highcharts
  const chartOptions = {
    chart: {
      type: "column",
    },
    title: {
      text: "Employee Hours",
    },
    xAxis: {
      categories: chartData.map((data) => data.employee),
      title: {
        text: "Employee",
      },
    },
    yAxis: {
      min: 0,
      title: {
        text: "Hours",
      },
    },
    series: [
      {
        name: "Hours",
        data: chartData.map((data) => data.hours),
      },
    ],
  };

  return (
    <div className="p-5 flex-1 w-1/2">
      <HighchartsReact highcharts={Highcharts} options={chartOptions} />
    </div>
  );
};

export default EmployeeHours;

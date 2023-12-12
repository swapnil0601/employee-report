import React from "react";
import EmployeeHours from "../components/EmployeeHours";
import ActivityDistribution from "../components/ActivityDistribution";

const ReportPage = () => {
  return (
    <div>
      <div className="flex gap-2">
        <EmployeeHours />
        <ActivityDistribution />
      </div>
    </div>
  );
};

export default ReportPage;

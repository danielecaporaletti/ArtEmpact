//Page3.jsx

function Page3({data}) {
  return (
    <div className="flex flex-col justify-start items-start h-full">
      <div className="text-white font-montserrat h-6 w-full font-bold">
        <p>L'Offerta'</p>
      </div>
      <div className="p-2 mt-4 w-full text-white font-jost text-base font-normal">
        <p>
         {data?.data.compatibilityCard.positionDescription}
        </p>
      </div>
      <div className="p-2 text-white font-jost text-base font-light">
        <ul className="mt-4">
          <li className="mb-2">📚 {data?.data.compatibilityCard.identifyEducationBackground.educationName}</li>
          <li className="mb-2">💼 {data?.data.compatibilityCard.identifyExperienceLevel.experienceName}</li>
          <li className="mb-2">💸 {data?.data.compatibilityCard.minProjectBudget} - {data?.data.compatibilityCard.maxProjectBudget}</li>
          <li className="mb-2">📍 {data?.data.compatibilityCard.identifyWorkPreference.workPreferenceName}</li>
        </ul>
      </div>
    </div>
  );
}

export default Page3;

import { createContext, useContext, useReducer } from "react";

const QueryContext = createContext();

const initialState = {
  title: "",
  description: "",
  minProjectBudget: 0,
  maxProjectBudget: 0,
  cardColor: "",
  identifyEducationBackground: null,
  identifyExperienceLevel: null,
  identifyProfessionalRelationship: null,
  positionDescription: "",
  identifyBusinessType: null,
  creativeSeeksBusinessLocations: [],
  identifyCreativeType: null,
  companyVisionMission: "",
  identifyWorkPreference: null,
  video1: null
};

function reducer(state, action) {
  switch (action.type) {
    case "card/create/page1":
      return {
        ...state,
        title: action.payload,
      };
    case "card/create/page2":
      return {
        ...state,
        video1: action.payload,
      };
    case "card/create/page3":
      return {
        ...state,
       creativeSeeksBusinessLocations: [...state.creativeSeeksBusinessLocations, action.payload],
      };
      case "card/create/page3/delete":
      return {
        ...state,
       creativeSeeksBusinessLocations: state.creativeSeeksBusinessLocations.filter((city) => city !== action.payload),
      };
    case "card/create/page41":
      return {
        ...state,
        description: action.payload,
      };
    case "card/create/page42":
      return {
        ...state,
        identifyEducationBackground: action.payload,
      };
    case "card/create/page43":
      return {
        ...state,
        identifyExperienceLevel: action.payload,
      };
    case "card/create/page44":
      return {
        ...state,
        identifyCreativeType: action.payload,
      };
    case "card/create/page51":
      return {
        ...state,
        positionDescription: action.payload,
      };
    case "card/create/page52":
      return {
        ...state,
        identifyProfessionalRelationship: action.payload,
      };
    case "card/create/page53":
      return {
        ...state,
        minProjectBudget: action.payload,
      };
    case "card/create/page54":
      return {
        ...state,
        maxProjectBudget: action.payload,
      };
    case "card/create/page45":
      return {
        ...state,
        identifyBusinessType: action.payload,
      };
    case "card/create/page46":
      return {
        ...state,
        companyVisionMission: action.payload,
      };
      case "card/create/page47":
      return {
        ...state,
        identifyWorkPreference: action.payload,
      };
      case "card/create/page48":
      return {
        ...state,
        identifyCreativeType: action.payload,
      };
    case "card/create/page6":
      return {
        ...state,
        cardColor: action.payload,
      };
    default:
      throw new Error("Action unkonwn");
  }
}

function QueryProvider({ children }) {
  const [
    {
      title,
      description,
      minProjectBudget,
      maxProjectBudget,
      cardColor,
      identifyEducationBackground,
      identifyExperienceLevel,
      identifyProfessionalRelationship,
      positionDescription,
      identifyBusinesType,
      identifyCreativeType,
      creativeSeeksBusinessLocations,
      companyVisionMission,
      identifyWorkPreference,
      video1,
    },
    dispatch,
  ] = useReducer(reducer, initialState);

  return (
    <QueryContext.Provider
      value={{
        title,
        description,
        minProjectBudget,
        maxProjectBudget,
        cardColor,
        identifyEducationBackground,
        identifyExperienceLevel,
        identifyProfessionalRelationship,
        positionDescription,
        identifyBusinesType,
        identifyCreativeType,
        creativeSeeksBusinessLocations,
        companyVisionMission,
        identifyWorkPreference,
        video1,
        dispatch,
      }}
    >
      {children}
    </QueryContext.Provider>
  );
}
function useCreateCard() {
  const context = useContext(QueryContext);
  if (context === undefined)
    throw new Error(
      "useCreateCard was used outside of the useCreateCardProvider"
    );
  return context;
}
export { QueryProvider, useCreateCard };

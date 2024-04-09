import { createContext, useContext, useReducer } from "react";

const QueryContext = createContext();

const initialState = {

};

function reducer(state, action) {
  switch (
    action.type
    //     case "search/update":
    //       return {
    //         ...state,
    //         search: action.payload,
    //         offset: 0,
    //       };

  ) {
  }
}

function QueryProvider({ children }) {
  const [
    {
      test,
   
    },
    dispatch,
  ] = useReducer(reducer, initialState);

  return (
    <QueryContext.Provider
      value={{
        test,
       
        dispatch,
      }}
    >
      {children}
    </QueryContext.Provider>
  );
}
function useQueryCust() {
  const context = useContext(QueryContext);
  if (context === undefined)
    throw new Error("QueryContext was used outside of the QueryProvider");
  return context;
}
export { QueryProvider, useQueryCust };

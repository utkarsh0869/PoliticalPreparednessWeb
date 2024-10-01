const API_BASE_URL = "http://localhost:8080";

export const loginUser = async (userData) => {
  try {
    const response = await fetch(`${API_BASE_URL}/users/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userData),
    });

    if (!response.ok) {
      const errorText = await response.text(); // Parse the response text
      throw new Error(errorText || "Login failed");
    }

    const result = await response.json();
    return result;
  } catch (error) {
    throw error;
  }
};

// Mock API call for user registration
export const registerUser = async (userData) => {
  //   try {
  //     const response = await fetch(`${API_BASE_URL}/users/register`, {
  //       method: "POST",
  //       headers: {
  //         "Content-Type": "application/json",
  //       },
  //       body: JSON.stringify(userData),
  //     });
  //     if (!response.ok) {
  //       const errorText = await response.text();
  //       throw new Error(errorText || "Registration failed");
  //     }
  //     const result = await response.json();
  //     return result;
  //   } catch (error) {
  //     throw error;
  //   }
};

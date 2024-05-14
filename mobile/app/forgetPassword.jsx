import { useState } from "react";
import { View, TextInput, TouchableOpacity, Text } from "react-native";
import React from "react";
import { Link } from "expo-router";

const forgetPassword = () => {
  const [email, setEmail] = useState("");
  return (
    <View className="flex-1 items-center justify-center bg-slate-600">
      <TextInput
        placeholder="Email"
        value={email}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TouchableOpacity className="bg-yellow-600 px-4 py-2 w-fit mx-auto rounded-xl">
        <Text className="text-white font-semibold text-lg">Send email</Text>
      </TouchableOpacity>
      <Link className="text-yellow-600 text-sm" href="/">
        Back to Login
      </Link>
      <Link className="text-yellow-600 text-sm" href="/register">
        Don't have an account
      </Link>
    </View>
  );
};

export default forgetPassword;

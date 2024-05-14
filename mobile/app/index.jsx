import { View, Text, TextInput, TouchableOpacity } from "react-native";
import React from "react";
import { Link } from "expo-router";

const index = () => {
  const [username, setUsername] = React.useState("");
  const [password, setPassword] = React.useState("");

  const handleLogin = () => {
    console.log(username, password);
  };

  return (
    <View className="flex-1 items-center justify-center bg-slate-600">
      <TextInput
        placeholder="Username"
        value={username}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TextInput
        placeholder="Password"
        value={password}
        className="w-4/5 border border-slate-500 rounded-xl px-4 py-2 mb-4 text-lg"
      />
      <TouchableOpacity className="bg-yellow-600 px-4 py-2 w-fit mx-auto rounded-xl">
        <Text className="text-white font-semibold text-lg">Login</Text>
      </TouchableOpacity>
      <View className="flex flex-row">
        <Text className="text-white text-sm">Don't have an account. then </Text>
        <Link href="/register" className="text-yellow-600 text-sm">
          Register now
        </Link>
      </View>
      <Link className="text-yellow-600 text-sm" href="/forgetPassword">
        forget password?
      </Link>
    </View>
  );
};

export default index;

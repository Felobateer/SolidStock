import { View, Text } from "react-native";
import React from "react";
import { StatusBar } from "expo-status-bar";
import { Link } from "expo-router";

const index = () => {
  return (
    <View className="flex-1 items-center justify-center bg-slate-600">
      <Text className="text-5xl">index</Text>
      <StatusBar style="auto" />
      <Link href="/test" style={{ fontSize: 20, color: "red" }}>
        Go to test
      </Link>
    </View>
  );
};

export default index;

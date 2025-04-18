export interface Stock {
  id: number;
  name: string;
  logo: string;
  finnhubIndustry: string;
  symbol: string;
  high: number;
  low: number;
  buy: number;
  sell: number;
  buyChange: number;
  sellChange: number;
  timestamp: Date;
  prevPrices: number[];
}

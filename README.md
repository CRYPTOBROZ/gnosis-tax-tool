# CRYPTOBROZ GNOSIS TAX TOOL

### The idea of the MVP product:

- is to fetch all gnosis pay wallet cahback transactions (EtherScanService)
- is to retrieve daily Gnosis token prices based on the timestamps of those cashback transactions (KrakenService)
  - Service that will fetch GNO price for some specific date
- calculate cashbacks euro value (TODO)
- calculate capital tax for each transaction for current year (TODO)
- provide api endpoint for fetching tax report for current year (TODO)

### Configure ETHERSCAN API key and Gnosis Wallet address.

[Etherscan API Key](https://docs.etherscan.io/getting-an-api-key)

```
etherscan.api.key=${ETHERSCAN_API_KEY}
gnosis.wallet.address=${GNOSIS_WALLET_ADDRESS}
```

## .ENV

Edit ENV variables in .env.example file, rename it to .env and load env variables.

How to reload env variables from `.env`.
`export $(grep -v '^#' .env | xargs)`

### API ENDPOINTS:

- `/api/v1/ohlc`
- `/api/v1/openclose`
- `/api/v1/cashbacks`
- `/api/v1/cashback/transactions`

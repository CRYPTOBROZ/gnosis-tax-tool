CRYPTOBROZ GNOSIS TAX TOOL

The idea of the MVP product:

- is to fetch all gnosis pay wallet cahback transactions (EtherScanService)
- is to retrieve daily Gnosis token prices based on the timestamps of those cashback transactions (TODO)
- calculate cashbacks euro value
- calculate capital tax for each transaction for current year

Configure ETHERSCAN API key and Gnosis Wallet address.

[Etherscan getting api key](https://docs.etherscan.io/getting-an-api-key)

```
etherscan.api.key=${ETHERSCAN_API_KEY}
etherscan.api.address=${GNOSIS_WALLET_ADDRESS}
```

API ENDPOINTS:

`/api/v1/cashback`

//SPDX-License-Identifier: MIT
 
pragma solidity ^0.8.0;

contract FurnitureSale{
    
    enum SaleState {deal, funded, delivery, approved, released}
    enum BuyFurniture {initiated, accepted, closed}
    
    struct sale{
        string shortCode;
        string description;
        uint256 value;
        SaleState saleState;
    }  
  
    int256 public totalSales = 0;
    address payable public furnitureCompanyAddress;
    address public clientAddress;
    BuyFurniture public buyFurniture;
    
    mapping(int256 => sale) public saleRegister;
  
	modifier condition(bool _condition) {
		require(_condition);
		_;
	}

	modifier onlyFurnitureCompany() {
		require(msg.sender == furnitureCompanyAddress);
		_;
	}

	modifier onlyClient() {
		require(msg.sender == clientAddress);
		_;
	}
	
	modifier bothClientFurnitureCompany(){
		require(msg.sender == clientAddress || msg.sender == furnitureCompanyAddress);
		_;	    
	}

	modifier inDeliveryState(BuyFurniture _state) {
		require(buyFurniture == _state);
		_;
	}

    modifier inSaleState(int256 _saleID, SaleState _state){
        require((_saleID <= totalSales - 1) && saleRegister[_saleID].saleState == _state);
        _;
    }

    modifier ampleFunding(int256 _saleID, uint256 _funding){
        require(saleRegister[_saleID].value == _funding);
        _;
    }

    modifier noMoreFunds(){
        require(address(this).balance == 0);
        _;
    }

    event saleAdded(string shortCode);
    event saleAccepted(address clientAddress);
    event saleFunded(int256 saleID);
    event saleStarted(int256 saleID);
    event saleApproved(int256 saleID);
    event fundsReleased(int256 saleID, uint256 valueReleased);
    event saleEnded();
    
    constructor()
    {
        furnitureCompanyAddress = payable(msg.sender);
        buyFurniture = BuyFurniture.initiated;
    }
    
    function addSale(string memory _shortCode, string memory _description, uint256 _value)
        public
        inDeliveryState(BuyFurniture.initiated)
        onlyFurnitureCompany
    {
        sale memory s;
        s.shortCode = _shortCode;
        s.description = _description;
        s.saleState = SaleState.deal;
        s.value = _value;
        saleRegister[totalSales] = s;
        totalSales++;
        emit saleAdded(_shortCode);
    }
    
    function acceptSale()
        public
        inDeliveryState(BuyFurniture.initiated)
    {
        clientAddress = msg.sender;
        buyFurniture = BuyFurniture.accepted;
        emit saleAccepted(msg.sender);
    }
    
    function fundSale(int256 _saleID)
        public
        payable
        inDeliveryState(BuyFurniture.accepted)
        inSaleState(_saleID, SaleState.deal)
        ampleFunding(_saleID, msg.value)
        onlyClient
    {
        saleRegister[_saleID].saleState = SaleState.funded;
        emit saleFunded(_saleID);
    }
    
    function startSale(int256 _saleID)
        public
        inDeliveryState(BuyFurniture.accepted)
        inSaleState(_saleID, SaleState.funded)
        onlyFurnitureCompany
    {
        saleRegister[_saleID].saleState = SaleState.delivery;
        emit saleStarted(_saleID);
    }

    function approveSale(int256 _saleID)
        public
        inDeliveryState(BuyFurniture.accepted)
        inSaleState(_saleID, SaleState.delivery)
        onlyClient
    {
        saleRegister[_saleID].saleState = SaleState.approved;
        emit saleApproved(_saleID);
    }
    
    function releaseFunds(int256 _saleID)
        public
        payable
        inDeliveryState(BuyFurniture.accepted)
        inSaleState(_saleID, SaleState.approved)
        onlyFurnitureCompany
    {
        furnitureCompanyAddress.transfer(saleRegister[_saleID].value);
        saleRegister[_saleID].saleState = SaleState.released;
        emit fundsReleased(_saleID, saleRegister[_saleID].value);
    }
    
    function endSale()
        public
        bothClientFurnitureCompany
        noMoreFunds
    {
        buyFurniture = BuyFurniture.closed;
        emit saleEnded();
    }
    
    function getBalance()
        public
        view
        returns (uint256 balance)
    {
        return address(this).balance;
    }
}
